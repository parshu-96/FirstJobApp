package com.embarkx.FirstJobApp.review;
import java.util.List;
public interface ReviewService {
    List<Review> getAllReviews(Long companyID);
    boolean addReview(Long companyId,Review review);
    Review getReviewById(Long companyId,Long reviewId);
    boolean updateReview(Long companyId,Long reviewId,Review review);
    boolean deleteReviewById(Long companyId,Long reviewId);
}
