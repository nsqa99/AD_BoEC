package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.NewsDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.NewsEntity;
import com.example.demo.repository.CategoryTestRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newRepository;

	@Autowired
	private CategoryTestRepository categoryRepository;
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<NewsDto> getPage(Pageable pageable, String keyword) {
		List<NewsDto> list = new ArrayList<>();

		if (keyword != null) {
			List<NewsEntity> entities = newRepository.findByTitleContaining(pageable, keyword);
			for (NewsEntity entity : entities) {
				NewsDto dto = new NewsDto(entity);
				list.add(dto);
			}
		} else {
			List<NewsEntity> entities = newRepository.findAll(pageable).getContent();
			for (NewsEntity entity : entities) {
				NewsDto dto = new NewsDto(entity);
				list.add(dto);
			}
		}

		return list;
	}

	@Override
	public Boolean deleteNew(Long id) {
		if (id != null) {
			newRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public NewsDto saveOrUpdate(NewsDto dto) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(dto.getCategoryCode());
		if (dto != null) {
			NewsEntity entity = null;
			if (dto.getId() != null) {
				entity = newRepository.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new NewsEntity();
			}
			entity.setCategory(categoryEntity);
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			entity.setShortDescription(dto.getShortDescription());
			entity.setThumbnail(dto.getThumbnail());

			entity = newRepository.save(entity);
			if (entity != null) {
				return new NewsDto(entity);
			}
		}
		return null;
	}

	@Override
	public NewsDto getNewById(Long id) {
		NewsEntity newEntity = newRepository.getOne(id);
		NewsDto dto = new NewsDto(newEntity);
		return dto;
	}

	@Override
	public Page<NewsDto> searchByPage(SearchDto dto) {

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.id ASC";
		String sqlCount = "select count(entity.id) from  NewEntity as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.NewDto(entity) from  NewEntity as entity where (1=1)  ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.title LIKE :text OR entity.content LIKE :text )";
		}
		
		if(dto.getCategory() != null) {
			whereClause += " AND ( entity.category.code LIKE :text )";
		}
		
		sql += whereClause + orderBy;
		sqlCount += whereClause;
		
		
		
		Query q = manager.createQuery(sql, NewsDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		
		if(dto.getCategory() != null) {
			q.setParameter("text", dto.getCategory());
			qCount.setParameter("text", dto.getCategory());
		}
		
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		
		@SuppressWarnings("unchecked")
		List<NewsDto> entities = q.getResultList();
		
		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<NewsDto> result = new PageImpl<NewsDto>(entities, pageable, count);
		return result;
	}
}
