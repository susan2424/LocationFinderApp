package com.example.locationfinderapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LocationDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 2;
    // Database columns
    public static String DB_NAME = "LocationsDB.db";
    public static String DB_TABLE = "LocationsTable";
    public static String COLUMN_ID = "LocationId";
    public static String COLUMN_ADDRESS = "LocationAddress";
    public static String COLUMN_LATITUDE = "LocationLatitude";
    public static String COLUMN_LONGITUDE = "LocationLongitude";
    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_FIRST_LAUNCH = "first_launch";
    private Context context;  // Add a Context reference

    public LocationDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;  // Store the context for later use
    }

    // Define database table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DB_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_LATITUDE + " TEXT," +
                COLUMN_LONGITUDE + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(query);

        // Call populateSampleData method to pre-load sample data
        populateSampleData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }

    // Adds entries in the table with an automatic ID
    public long AddLocation(ModelLocation locationModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ADDRESS, locationModel.getLocationAddress());
        contentValues.put(COLUMN_LATITUDE, locationModel.getLocationLatitude());
        contentValues.put(COLUMN_LONGITUDE, locationModel.getLocationLongitude());
        long ID = db.insert(DB_TABLE, null, contentValues);
        Log.d("Inserted", "id ->" + ID);
        return ID;
    }

    // Populates the database with the 100 locations
    private void populateSampleData(SQLiteDatabase db) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isFirstLaunch = prefs.getBoolean(KEY_FIRST_LAUNCH, true);

        if (isFirstLaunch) {
            // all 100 locations into the database
            insertData(db, "Oshawa", "43.8971", "-78.8658");
            insertData(db, "Ajax", "43.8501", "-79.0329");
            insertData(db, "Pickering", "43.8384", "-79.0868");
            insertData(db, "Scarborough", "43.7731", "-79.2577");
            insertData(db, "Downtown Toronto", "43.6532", "-79.3832");
            insertData(db, "Mississauga", "43.5890", "-79.6441");
            insertData(db, "Brampton", "43.7315", "-79.7624");
            insertData(db, "Markham", "43.8561", "-79.3370");
            insertData(db, "Vaughan", "43.8390", "-79.5085");
            insertData(db, "Richmond Hill", "43.8775", "-79.4377");
            insertData(db, "Aurora", "44.0060", "-79.4530");
            insertData(db, "Newmarket", "44.0500", "-79.4665");
            insertData(db, "East Gwillimbury", "44.1008", "-79.4665");
            insertData(db, "Halton Hills", "43.6250", "-79.9070");
            insertData(db, "Burlington", "43.3265", "-79.7990");
            insertData(db, "Oakville", "43.4443", "-79.6877");
            insertData(db, "Milton", "43.5237", "-79.8873");
            insertData(db, "Caledon", "43.8510", "-79.8353");
            insertData(db, "Georgetown", "43.6481", "-79.9142");
            insertData(db, "Etobicoke", "43.6058", "-79.5526");
            insertData(db, "North York", "43.7545", "-79.4101");
            insertData(db, "Yorkville", "43.6702", "-79.3923");
            insertData(db, "Forest Hill", "43.7037", "-79.4074");
            insertData(db, "Leslieville", "43.6717", "-79.3200");
            insertData(db, "Kensington Market", "43.6545", "-79.4029");
            insertData(db, "York Region", "43.8780", "-79.4974");
            insertData(db, "Markham Village", "43.8652", "-79.2605");
            insertData(db, "Kleinburg", "43.8451", "-79.6022");
            insertData(db, "Mount Pleasant", "43.7126", "-79.4048");
            insertData(db, "Rosedale", "43.6830", "-79.3735");
            insertData(db, "The Beaches", "43.6763", "-79.2937");
            insertData(db, "Toronto Islands", "43.6234", "-79.3823");
            insertData(db, "Liberty Village", "43.6340", "-79.4186");
            insertData(db, "Cabbagetown", "43.6644", "-79.3671");
            insertData(db, "Distillery District", "43.6544", "-79.3575");
            insertData(db, "Harbourfront", "43.6345", "-79.3834");
            insertData(db, "High Park", "43.6506", "-79.4633");
            insertData(db, "Riverdale", "43.6703", "-79.3576");
            insertData(db, "Sunnyside", "43.6198", "-79.4553");
            insertData(db, "East York", "43.6832", "-79.3279");
            insertData(db, "West Hill", "43.7706", "-79.1937");
            insertData(db, "Mimico", "43.6052", "-79.4962");
            insertData(db, "Alderwood", "43.5994", "-79.5360");
            insertData(db, "Weston", "43.7066", "-79.5289");
            insertData(db, "Islington", "43.6057", "-79.5107");
            insertData(db, "Bayview Village", "43.7578", "-79.3737");
            insertData(db, "College Park", "43.7537", "-79.3807");
            insertData(db, "Galleria Shopping Centre", "43.7405", "-79.4453");
            insertData(db, "Sherwood Park", "43.7463", "-79.3532");
            insertData(db, "Bloor West Village", "43.6467", "-79.4869");
            insertData(db, "Roncesvalles", "43.6513", "-79.4662");
            insertData(db, "Carleton Village", "43.6595", "-79.4742");
            insertData(db, "Dundas West", "43.6420", "-79.4740");
            insertData(db, "Jane and Finch", "43.7261", "-79.5080");
            insertData(db, "Oakwood Village", "43.6920", "-79.4480");
            insertData(db, "East York", "43.6881", "-79.3300");
            insertData(db, "Bathurst Manor", "43.6990", "-79.4483");
            insertData(db, "Weston Village", "43.7072", "-79.5178");
            insertData(db, "Fairview Mall", "43.7355", "-79.3685");
            insertData(db, "Scarborough Town Centre", "43.7598", "-79.2371");
            insertData(db, "Pacific Mall", "43.7864", "-79.1970");
            insertData(db, "Yorkdale Shopping Centre", "43.7270", "-79.4396");
            insertData(db, "Toronto Pearson International Airport", "43.6813", "-79.6107");
            insertData(db, "401 Richmond", "43.6491", "-79.3863");
            insertData(db, "Bayview Village", "43.7578", "-79.3737");
            insertData(db, "St. Lawrence Market", "43.6507", "-79.3742");
            insertData(db, "Nathan Phillips Square", "43.6533", "-79.3832");
            insertData(db, "Ontario Science Centre", "43.6895", "-79.3487");
            insertData(db, "Royal Ontario Museum", "43.6670", "-79.3942");
            insertData(db, "Art Gallery of Ontario", "43.6536", "-79.3840");
            insertData(db, "Toronto City Hall", "43.6532", "-79.3815");
            insertData(db, "BMO Field", "43.6347", "-79.4018");
            insertData(db, "Ontario Place", "43.6227", "-79.4351");
            insertData(db, "Toronto Zoo", "43.7860", "-79.1992");
            insertData(db, "Black Creek Pioneer Village", "43.7066", "-79.5700");
            insertData(db, "Toronto Botanical Garden", "43.7064", "-79.3910");
            insertData(db, "Canada's Wonderland", "43.5870", "-79.6085");
            insertData(db, "Sherwood Park", "43.7460", "-79.3435");
            insertData(db, "Humber Bay Shores", "43.6056", "-79.4937");
            insertData(db, "High Park Nature Centre", "43.6464", "-79.4637");
            insertData(db, "Tommy Thompson Park", "43.6136", "-79.5227");
            insertData(db, "Rouge National Urban Park", "43.8695", "-79.2267");
            insertData(db, "G. Ross Lord Park", "43.7635", "-79.5362");
            insertData(db, "The Brick Works", "43.6744", "-79.4042");
            insertData(db, "Colonel Samuel Smith Park", "43.6281", "-79.5526");
            insertData(db, "Erindale Park", "43.5936", "-79.6647");
            insertData(db, "Heart Lake Conservation Area", "43.6700", "-79.7620");

            // Update SharedPreferences to mark that data is inserted
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_FIRST_LAUNCH, false);
            editor.apply();
        }

    }

    // Helper method to insert data into the database
    private void insertData(SQLiteDatabase db, String address, String latitude, String longitude) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longitude);
        db.insert(DB_TABLE, null, contentValues);
    }

    // Returns a list of entries in the database to display on the main page
    public List<ModelLocation> getLocation() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelLocation> allLocation = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);
        if(cursor.moveToFirst()) {
            do {
                ModelLocation locationModel = new ModelLocation();
                locationModel.setId(cursor.getInt(0));
                locationModel.setLocationAddress(cursor.getString(1));
                locationModel.setLocationLatitude(cursor.getString(2));
                locationModel.setLocationLongitude(cursor.getString(3));
                allLocation.add(locationModel);
            } while(cursor.moveToNext());
        }
        return allLocation;
    }

    // to get info of a specific location based on its ID
    public ModelLocation getLocation(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String [] query = new String[] {COLUMN_ID, COLUMN_ADDRESS, COLUMN_LATITUDE, COLUMN_LONGITUDE};
        Cursor cursor = db.query(DB_TABLE, query, COLUMN_ID+"=?", new String[]{String.valueOf(ID)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new ModelLocation(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    // To delete a location from the database
    void deleteLocation(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DB_TABLE, COLUMN_ID+"=?", new String[]{String.valueOf(ID)});
        db.close();
    }

    // Updates an existing location with new values
    public int updateLocation(int id, ModelLocation updatedLocation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_ADDRESS, updatedLocation.getLocationAddress());
        updatedValues.put(COLUMN_LATITUDE, updatedLocation.getLocationLatitude());
        updatedValues.put(COLUMN_LONGITUDE, updatedLocation.getLocationLongitude());
        return db.update(
                DB_TABLE,
                updatedValues,
                COLUMN_ID + " =?",
                new String[]{String.valueOf(id)}
        );
    }
}
