package se.ocarina.straightonstrait.dagger.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.ocarina.straightonstrait.service.CoordLocation;
import se.ocarina.straightonstrait.service.Infotext;
import se.ocarina.straightonstrait.service.JourneyName;
import se.ocarina.straightonstrait.service.JourneyType;
import se.ocarina.straightonstrait.service.Leg;
import se.ocarina.straightonstrait.service.Link;
import se.ocarina.straightonstrait.service.Message;
import se.ocarina.straightonstrait.service.Note;
import se.ocarina.straightonstrait.service.PricingSet;
import se.ocarina.straightonstrait.service.StopLocation;
import se.ocarina.straightonstrait.service.Ticket;
import se.ocarina.straightonstrait.service.Trip;

@Module
public class NetModule {
    private final String mBaseUrl;

    public NetModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        Type infotextListType = new TypeToken<List<Infotext>>() {}.getType();
        SingleElementToListDeserializer<Infotext> infotextAdapter =
                new SingleElementToListDeserializer<>(Infotext.class);

        Type journeyNameListType = new TypeToken<List<JourneyName>>() {}.getType();
        SingleElementToListDeserializer<JourneyName> journeyNameAdapter =
                new SingleElementToListDeserializer<>(JourneyName.class);

        Type journeyTypeListType = new TypeToken<List<JourneyType>>() {}.getType();
        SingleElementToListDeserializer<JourneyType> journeyTypeAdapter =
                new SingleElementToListDeserializer<>(JourneyType.class);

        Type noteListType = new TypeToken<List<Note>>() {}.getType();
        SingleElementToListDeserializer<Note> noteAdapter =
                new SingleElementToListDeserializer<>(Note.class);

        Type linkListType = new TypeToken<List<Link>>() {}.getType();
        SingleElementToListDeserializer<Link> linkAdapter =
                new SingleElementToListDeserializer<>(Link.class);

        Type coordLocationListType = new TypeToken<List<CoordLocation>>() {}.getType();
        SingleElementToListDeserializer<CoordLocation> coordLocationAdapter =
                new SingleElementToListDeserializer<>(CoordLocation.class);

        Type stopLocationListType = new TypeToken<List<StopLocation>>() {}.getType();
        SingleElementToListDeserializer<StopLocation> stopLocationAdapter =
                new SingleElementToListDeserializer<>(StopLocation.class);

        Type messageListType = new TypeToken<List<Message>>() {}.getType();
        SingleElementToListDeserializer<Message> messageAdapter =
                new SingleElementToListDeserializer<>(Message.class);

        Type ticketListType = new TypeToken<List<Ticket>>() {}.getType();
        SingleElementToListDeserializer<Ticket> ticketAdapter =
                new SingleElementToListDeserializer<>(Ticket.class);

        Type pricingSetListType = new TypeToken<List<PricingSet>>() {}.getType();
        SingleElementToListDeserializer<PricingSet> pricingSetAdapter =
                new SingleElementToListDeserializer<>(PricingSet.class);

        Type legListType = new TypeToken<List<Leg>>() {}.getType();
        SingleElementToListDeserializer<Leg> legAdapter =
                new SingleElementToListDeserializer<>(Leg.class);

        Type tripListType = new TypeToken<List<Trip>>() {}.getType();
        SingleElementToListDeserializer<Trip> tripAdapter =
                new SingleElementToListDeserializer<>(Trip.class);

        return new GsonBuilder()
                .registerTypeAdapter(infotextListType, infotextAdapter)
                .registerTypeAdapter(journeyNameListType, journeyNameAdapter)
                .registerTypeAdapter(journeyTypeListType, journeyTypeAdapter)
                .registerTypeAdapter(noteListType, noteAdapter)
                .registerTypeAdapter(linkListType, linkAdapter)
                .registerTypeAdapter(coordLocationListType, coordLocationAdapter)
                .registerTypeAdapter(stopLocationListType, stopLocationAdapter)
                .registerTypeAdapter(messageListType, messageAdapter)
                .registerTypeAdapter(ticketListType, ticketAdapter)
                .registerTypeAdapter(pricingSetListType, pricingSetAdapter)
                .registerTypeAdapter(legListType, legAdapter)
                .registerTypeAdapter(tripListType, tripAdapter)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);

        // Add logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.addInterceptor(logging);

        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

}
