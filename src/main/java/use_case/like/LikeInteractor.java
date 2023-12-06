package use_case.like;

public class LikeInteractor implements LikeInputBoundary {
    final LikeDataAccessInterface likeDataAccessObject;
    final LikeOutputBoundary likePresenter;

    public LikeInteractor(LikeDataAccessInterface likeDataAccessInterface,
                           LikeOutputBoundary likeOutputBoundary) {
        this.likeDataAccessObject = likeDataAccessInterface;
        this.likePresenter = likeOutputBoundary;
    }

    @Override
    public void execute(LikeInputData likeInputData) {
        // TODO Auto-generated method stub  
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}