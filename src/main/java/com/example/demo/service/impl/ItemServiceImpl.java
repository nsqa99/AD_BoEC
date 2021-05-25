package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.convert.Converter;
import com.example.demo.dto.ItemDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Image;
import com.example.demo.entity.Item;
import com.example.demo.entity.Publisher;
import com.example.demo.entity.SubCategory;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private ItemRepository productRepos;

	@Autowired
	private AuthorRepository authorRepos;

	@Autowired
	private PublisherRepository publisherRepos;

	@Autowired
	private CategoryRepository categoryRepos;

	@Autowired
	private SubCategoryRepository subcategoryRepos;

	@Autowired
	private ImageRepository imageRepos;

	@Override
	public Page<ItemDto> searchByPage(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.id DESC";
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.ProductDto(entity) from  Product as entity where (1=1)  ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.description LIKE :text )";
		}

		if (dto.getCategory() != null) {
			whereClause += " AND ( entity.category.code LIKE :category )";
		}
		if (dto.getSubcategory() != null) {
			whereClause += " AND ( entity.subcategory.code LIKE :subcategory )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, ItemDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		if (dto.getCategory() != null) {
			q.setParameter("category", dto.getCategory());
			qCount.setParameter("category", dto.getCategory());
		}

		if (dto.getSubcategory() != null) {
			q.setParameter("subcategory", dto.getSubcategory());
			qCount.setParameter("subcategory", dto.getSubcategory());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ItemDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ItemDto> result = new PageImpl<ItemDto>(entities, pageable, count);
		return result;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	public ItemDto saveOrUpdate(ItemDto dto) {
		if (dto != null) {

			Set<String> authorNames = dto.getAuthors();
			Set<Author> authors = new HashSet<>();

			String publisherName = dto.getPublisher();

			String categoryCode = dto.getCategoryCode();
			int inStock = dto.getInStock();

			List<String> imageUrls = dto.getImages();
			List<Image> images = new ArrayList<>();

			Item entity = null;
			Author author = null;
			Publisher publisher = null;
			Image image = null;

			if (categoryCode.equalsIgnoreCase("sach")) {
				publisher = publisherRepos.findByName(publisherName);
			}

			Category category = categoryRepos.findOneByCode(categoryCode);
			SubCategory subcategory = subcategoryRepos.findOneByCode(dto.getSubcategoryCode());

			if (dto.getId() != null) {
				entity = productRepos.getOne(dto.getId());
				images = imageRepos.findAllByItemId(dto.getId());
				authors = entity.getBook().getAuthors();
//				publishers = entity.getBook().getPublisher();

				for (Author authorTmp : authors) {
					author = authorTmp;
				}

				if (dto.getCategoryCode().equalsIgnoreCase("sach")) {
					if (publisher == null)
						publisherRepos.save(new Publisher(publisherName));
					for (String authorName : authorNames) {
						author = authorRepos.findByName(authorName);
						if (author != null) {
							authors.add(author);
						} else {
							author = new Author(authorName);
							System.out.println(author.getName());
							authorRepos.save(author);
							authors.add(author);
						}
					}
				}

//				for (Publisher publisherTmp : publishers) {
//					publisher = publisherTmp;
//				}

//				publisher = publisherRepos.findByName(publisherName);
//				if (publisher != null) {
//					publishers.add(publisher);
//				} else {
//					publisher = new Publisher(publisherName);
//					publisherRepos.save(publisher);
//					publishers.add(publisher);
//				}

				for (Image imageTmp : images) {
					image = imageTmp;
				}
				for (int i = 0; i < images.size(); i++) {
					if (images.size() == imageUrls.size()) {
						images.get(i).setUrl(imageUrls.get(i));
					} else if (images.size() > imageUrls.size()) {
//						for (int j = imageUrls.size(); j < images.size(); j++) {
////							images.remove(j);
//							System.out.println(images.get(j).getId());
//							imageRepos.deleteById(images.get(j).getId());
//							continue;
//						}
//						for (int j = 0; j < imageUrls.size(); j++) {
//							images.get(j).setUrl(imageUrls.get(j));
//						}

					} else {
						for (int j = images.size(); j < imageUrls.size(); j++) {
							image = new Image(imageUrls.get(j));
							images.add(image);
						}
					}
				}
			}
			if (entity == null) {
				entity = new Item();

				if (dto.getCategoryCode().equalsIgnoreCase("sach")) {
					for (String authorName : authorNames) {
						author = authorRepos.findByName(authorName);

						if (author != null) {
							authors.add(author);
						} else {
							author = new Author(authorName);
							System.out.println(author.getName());
							authorRepos.save(author);
							authors.add(author);
						}
					}
				}

//				publisher = publisherRepos.findByName(publisherName);
//				if (publisher != null) {
//					publishers.add(publisher);
//				} else {
//					publisher = new Publisher(publisherName);
//					publisherRepos.save(publisher);
//					publishers.add(publisher);
//				}

				for (String imageUrl : imageUrls) {
					image = new Image(imageUrl);
					images.add(image);
				}
			}
			entity.setBook(new Book());
			entity.setType(dto.getType());
			entity.setName(dto.getName());
//			entity.setSlug(Slug.makeSlug(dto.getName()));
			entity.setInStock(inStock);
			entity.setDescription(dto.getDescription());
			entity.setPrice(dto.getPrice());
			entity.setCategory(category);
			entity.setSubcategory(subcategory);
			entity.setImages(images);
			if (dto.getCategoryCode().equalsIgnoreCase("sach")) {
				entity.getBook().setYear(dto.getPublishingYear());
				entity.getBook().setNumber(dto.getNumberOfPages());
			}

			for (int i = 0; i < images.size(); i++) {
				images.get(i).setItem(entity);
			}
			entity.getBook().setAuthors(authors);
			entity.getBook().setPublisher(publisher);
			entity.getBook().setItem(entity);

			entity = productRepos.save(entity);

			if (author != null) {
				author = authorRepos.save(author);
			}
//			if (publisher != null) {
//				publisher = publisherRepos.save(publisher);
//			}
			image = imageRepos.save(image);

			if (entity != null) {
				return new ItemDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			productRepos.deleteById(id);

			return true;
		}
		return false;
	}

	@Override
	public ItemDto getProductById(Long id) {
		Item product = productRepos.getOne(id);
		ItemDto dto = new ItemDto(product);
		return dto;
	}

	@Override
	public List<ItemDto> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		List<ItemDto> results = productRepos.findAll(pageable).getContent().stream().map(item -> new ItemDto(item))
				.collect(Collectors.toList());

		return results;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	public ItemDto insert(ItemDto dto) {
		Item item = new Item();
		Book book = new Book();
		Publisher pub = new Publisher(dto.getPublisher());
		SubCategory subcategory = subcategoryRepos.findOneByCode(dto.getSubcategoryCode());
		Category category = categoryRepos.findOneByCode(dto.getCategoryCode());

		List<String> imageUrls = dto.getImages();
		List<Image> images = imageUrls.stream().map(image -> new Image(image)).collect(Collectors.toList());
		Set<String> authors = new HashSet<>();
		if (dto.getCategoryCode().equals("sach")) {
			authors = dto.getAuthors();
			book.setPublisher(pub);
			book.setNumber(dto.getNumberOfPages());
			book.setYear(dto.getPublishingYear());
			book.setAuthors(authors.stream().map(name -> new Author(name)).collect(Collectors.toSet()));
			item.setBook(book);

		}

		item.setName(dto.getName());
		item.setPrice(dto.getPrice());
		item.setType(dto.getType());
		item.setDescription(dto.getDescription());
		item.setInStock(dto.getInStock());
		item.setSubcategory(subcategory);
		item.setCategory(category);

		Item newItem = productRepos.save(item);
		List<Image> newImages = images.stream().map(image -> {
			image.setItem(newItem);
			return imageRepos.save(image);
		}).collect(Collectors.toList());
		newItem.setImages(newImages);
		ItemDto result = new ItemDto(newItem);
		return result;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	public ItemDto update(ItemDto dto) {
		Item item = productRepos.findById(dto.getId()).orElse(null);
		if (item == null)
			return null;

		Book book = new Book();
		Publisher pub = new Publisher(dto.getPublisher());
		SubCategory subcategory = subcategoryRepos.findOneByCode(dto.getSubcategoryCode());
		Category category = categoryRepos.findOneByCode(dto.getCategoryCode());

		List<String> imageUrls = dto.getImages();
		List<Image> images = imageUrls.stream().map(image -> new Image(image)).collect(Collectors.toList());
		Set<String> authors = new HashSet<>();
		if (dto.getCategoryCode().equals("sach")) {
			authors = dto.getAuthors();
			book.setPublisher(pub);
			book.setNumber(dto.getNumberOfPages());
			book.setYear(dto.getPublishingYear());
			book.setAuthors(authors.stream().map(name -> new Author(name)).collect(Collectors.toSet()));
			item.setBook(book);

		}

		item.setName(dto.getName());
		item.setPrice(dto.getPrice());
		item.setType(dto.getType());
		item.setDescription(dto.getDescription());
		item.setInStock(dto.getInStock());
		item.setSubcategory(subcategory);
		item.setCategory(category);

		Item newItem = productRepos.save(item);
		List<Image> newImages = images.stream().map(image -> {
			image.setItem(newItem);
			return imageRepos.save(image);
		}).collect(Collectors.toList());
		newItem.setImages(newImages);
		ItemDto result = new ItemDto(newItem);
		return result;
	}

	@Override
	public long getTotal() {
		return productRepos.count();
	}

	@Override
	public List<ItemDto> findAllByCategory(String match, Pageable pageable) {
		List<ItemDto> results = 
				Converter.toPage(productRepos.findAllByCategory(match), pageable)
				.getContent()
				.stream().map(item -> new ItemDto(item)).collect(Collectors.toList());

		return results;
	}

	@Override
	public List<ItemDto> findAllBySubcategory(String match, Pageable pageable) {
		List<ItemDto> results = 
				Converter.toPage(productRepos.findAllBySubcategory(match), pageable)
				.getContent()
				.stream().map(item -> new ItemDto(item)).collect(Collectors.toList());

		return results;
	}

	@Override
	public long getTotalByCategory(String cate) {
		return productRepos.getToTalByCategory(cate);
	}

	@Override
	public long getTotalBySubcategory(String sub) {
		return productRepos.getToTalBySubcategory(sub);
	}

}
