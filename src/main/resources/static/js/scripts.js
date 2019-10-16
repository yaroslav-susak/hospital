"use strict";

function editReview(id) {
    var review = document.getElementById(id);

    var editReviewForm = review.getElementsByClassName("edit-review-form")[0];
    var deleteReviewButton = review.getElementsByClassName("delete-review-button")[0];
    var reviewText = review.getElementsByClassName("review__text")[0];
    var editReviewButton = review.getElementsByClassName("edit-review-button")[0];

    editReviewForm.style.display = "block";

    reviewText.style.display = "none";
    editReviewButton.style.display = "none";
    deleteReviewButton.style.display = "none";
}