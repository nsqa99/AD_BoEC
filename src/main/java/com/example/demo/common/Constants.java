package com.example.demo.common;

public class Constants {
	public static enum ProductType {

		Book(1), // sach
		Clothes(2), // quan ao
		Electric(3), // do dien tu
		;

		private int value;

		private ProductType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	public static enum StatusCode {
		OK(200),
		BAD_REQUEST(400),
		INTERNAL_ERROR(500),
		CREATE_ERROR(409)
		;
		private int value;

		private StatusCode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

}
