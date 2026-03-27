-keep class com.climo.weather.model.** { *; }
-keep class com.climo.weather.network.** { *; }
-keep class com.climo.weather.repository.** { *; }
-keep class com.climo.weather.viewmodel.** { *; }
-keep class com.climo.weather.util.** { *; }

-keepclassmembers class * {
    @kotlinx.serialization.Serializable <fields>;
}

-keep class kotlinx.serialization.** { *; }
-keepclassmembers class kotlinx.serialization.** {
    *** Companion;
}

-keep class com.jakewharton.retrofit2.converter.kotlinx.serialization.** { *; }

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.**
