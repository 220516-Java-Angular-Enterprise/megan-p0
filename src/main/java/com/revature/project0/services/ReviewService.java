package com.revature.project0.services;

import com.revature.project0.models.Review;
import com.revature.project0.daos.ReviewDAO;

import java.util.List;

public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public List<Review> getReviewByProd(String id) {
        return reviewDAO.getReviewsByProdId(id);
    }

}
