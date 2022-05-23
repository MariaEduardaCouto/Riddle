package com.app.riddle.scenes.feed.interfaces;

public interface IFeedActivity {
    void showSpinner();
    void hideSpinner();
    void presentContent();
    void presentError(String error);

    void navigateToCreate();
}
