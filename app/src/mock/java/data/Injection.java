package data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.de.data.DERepository;
import com.de.data.local.DELocalDataSource;
import com.de.data.remote.DERemoteDataSource;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class Injection {

    public static DERepository provideDERepository(@NonNull Context context) {
        checkNotNull(context);
        return DERepository.getInstance(DERemoteDataSource.getInstance(context),
                DELocalDataSource.getInstance(context));
    }
}