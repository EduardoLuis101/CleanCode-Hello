package es.ulpgc.eite.cleancode.helloworld.bye;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.helloworld.app.AppMediator;
import es.ulpgc.eite.cleancode.helloworld.app.ByeToNextState;
import es.ulpgc.eite.cleancode.helloworld.app.ByeToPreviousState;
import es.ulpgc.eite.cleancode.helloworld.app.HelloToByeState;
import es.ulpgc.eite.cleancode.helloworld.app.NextToByeState;
import es.ulpgc.eite.cleancode.helloworld.app.PreviousToByeState;

public class ByePresenter implements ByeContract.Presenter {

    public static String TAG = ByePresenter.class.getSimpleName();

    private WeakReference<ByeContract.View> view;
    private ByeState state;
    private ByeContract.Model model;
    private AppMediator mediator;

    public ByePresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getByeState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state
        state = new ByeState();

        // call the model and update the state
        state.data = model.getStoredData();

        // use passed state if is necessary
        HelloToByeState savedState = getStateFromHelloScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.message);

            // update the state if is necessary
            state.data = savedState.message;
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        NextToByeState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.message);

            // update the state if is necessary
            state.data = savedState.message;
        }

        // call the model and update the state
        state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

    }

    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    @Override
    public void sayByeButtonClicked() {

    }

    @Override
    public void goHelloButtonClicked() {

    }

    private NextToByeState getStateFromNextScreen() {
        return mediator.getNextByeScreenState();
    }

    private void passStateToNextScreen(ByeToNextState state) {
        mediator.setNextByeScreenState(state);
    }

    private void passStateToPreviousScreen(ByeToPreviousState state) {
        mediator.setPreviousByeScreenState(state);
    }

    private PreviousToByeState getStateFromPreviousScreen() {
        return mediator.getPreviousByeScreenState();
    }

    private HelloToByeState getStateFromHelloScreen(){
        return mediator.getHelloToByeState();
    }

    @Override
    public void injectView(WeakReference<ByeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ByeContract.Model model) {
        this.model = model;
    }

}