package com.nashss.se.digitalnomad.lambda;

import com.nashss.se.digitalnomad.activity.requests.SaveFavoritesRequest;
import com.nashss.se.digitalnomad.activity.results.SaveFavoritesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class SaveFavoritesLambda extends LambdaActivityRunner<SaveFavoritesRequest, SaveFavoritesResult>
        implements RequestHandler<LambdaRequest<SaveFavoritesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SaveFavoritesRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody(SaveFavoritesRequest.class),
            (request, serviceComponent) ->
            serviceComponent.provideSaveFavoritesActivity().handleRequest(request)
        );
    }
}


