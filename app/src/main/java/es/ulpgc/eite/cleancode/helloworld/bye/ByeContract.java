package es.ulpgc.eite.cleancode.helloworld.bye;

import java.lang.ref.WeakReference;

public interface ByeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayByeData(ByeViewModel viewModel);

        void onDataUpdated(ByeViewModel viewModel);

        void navigateToNextScreen();

        void navigateToHelloScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();

        void sayByeButtonClicked();

        void goHelloButtonClicked();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}
