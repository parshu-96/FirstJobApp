package com.embarkx.FirstJobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId)
    {
        System.out.println("In Reviews");
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review) {
        if (reviewService.addReview(companyId, review))
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Cannot add review", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long reviewId)
    {
        return new ResponseEntity<>(reviewService.getReviewById(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review)
    {
        if(reviewService.updateReview(companyId,reviewId,review))
        return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        else
        return new ResponseEntity<>("Review not Updated",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId)
    {
        if(reviewService.deleteReviewById(companyId,reviewId))
        return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
    else
        return new ResponseEntity<>("Review not Deleted",HttpStatus.NOT_FOUND);
    }


}
