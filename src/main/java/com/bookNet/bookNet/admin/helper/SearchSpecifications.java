package com.bookNet.bookNet.admin.helper;

import com.bookNet.bookNet.common.SearchCondition;
import com.bookNet.bookNet.models.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SearchSpecifications {

    public static Specification<Book> hasBookName(String bookName) {

        return (root, query, criteriaBuilder) -> {
            if (bookName == null || bookName.isEmpty()) {
                return   criteriaBuilder.conjunction(); // Always true
            }
            return criteriaBuilder.equal(root.get("bookName"),  bookName );
        };
    }

    public static Specification<Book> hasAuthorName(String authorName) {

        return (root, query, criteriaBuilder) -> {
            if (authorName == null || authorName.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("authorName")), "%" + authorName + "%");
        };
    }

    public static Specification<Book> hasBookTitle(String bookTitle) {

        return (root, query, criteriaBuilder) -> {
            if (bookTitle == null || bookTitle.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }
            return criteriaBuilder.equal(root.get("bookTitle") , bookTitle );
        };
    }

    public static Specification<Book> hasCategory(String category) {

        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }
            return criteriaBuilder.equal(root.get("category"),  category );
        };
    }

    public static Specification<Book> hasAvailableStock(int availableStock, SearchCondition searchCondition) {

        return (root, query, criteriaBuilder) -> {
            if (availableStock <= 0) {
                System.out.println("Working with negative stock is not allowed");
                return criteriaBuilder.conjunction(); // Always true
            }

            switch(searchCondition){
                case EQUAL:
                    return criteriaBuilder.equal(root.get("remainingStock"), availableStock);
                case LESS_THAN:
                    return criteriaBuilder.lessThan(root.get("remainingStock"), availableStock);
                case GREATER_THAN:
                    return criteriaBuilder.greaterThan(root.get("remainingStock"), availableStock);
                case LESS_THAN_OR_EQUAL:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("remainingStock"), availableStock);
                case GREATER_THAN_OR_EQUAL:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("remainingStock"), availableStock);
                default:
                    return criteriaBuilder.conjunction(); // Always true
            }
        };
    }
}
