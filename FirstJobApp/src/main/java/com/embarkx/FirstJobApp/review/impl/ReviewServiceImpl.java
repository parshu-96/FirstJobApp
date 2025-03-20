package com.embarkx.FirstJobApp.review.impl;

import com.embarkx.FirstJobApp.company.Company;
import com.embarkx.FirstJobApp.company.CompanyService;
import com.embarkx.FirstJobApp.review.Review;
import com.embarkx.FirstJobApp.review.ReviewRepository;
import com.embarkx.FirstJobApp.review.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepo;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepo,CompanyService companyService) {
        this.reviewRepo = reviewRepo;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyID) {
        List<Review> reviews=reviewRepo.findByCompanyId(companyID);
        return reviews;
    }
 
    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getCompanyById(companyId);
        if(company!=null) {
            review.setCompany(company);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepo.findByCompanyId(companyId);

        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId)!=null)
        {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepo.save(updatedReview);
            return true;
        }
        else
        return  false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null && reviewRepo.existsById(reviewId))
        {
            Review review=reviewRepo.findById(reviewId).orElse(null);
            Company company=review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company,companyId);
            reviewRepo.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
