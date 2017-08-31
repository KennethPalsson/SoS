package se.ocarina.straightonstrait.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    @GET("location?format=json")
    Call<LocationRoot> getLocation(
            @Query("input") String input);

    @GET("trip?format=json")
    Call<TripRoot> getTrip(
            @Query("originId") String originId,
            @Query("destId") String destId,
            @Query("time") String time);

    @GET("journeyDetail")
    Call<JourneyDetailRoot> getJourneyDetail(
            @Query("ref") String ref);

    @GET("departureBoard?format=json")
    Call<DepartureBoardRoot> getDepartureBoard(
            @Query("id") String id,
            @Query("time") String time);

    @GET("arrivalBoard?format=json")
    Call<ArrivalBoardRoot> getArrivalBoard(
            @Query("id") String id,
            @Query("time") String time);

}
