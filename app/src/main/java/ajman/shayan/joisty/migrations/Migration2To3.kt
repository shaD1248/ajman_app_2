package ajman.shayan.joisty.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2To3: Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("INSERT INTO loading ('label', 'including_self_weight', 'wd', 'wl') VALUES ('Commercial',  'false', '250.0', '600.0')")
        database.execSQL("INSERT INTO loading ('label', 'including_self_weight', 'wd', 'wl') VALUES ('Parking',     'false', '250.0', '300.0')")
        database.execSQL("INSERT INTO loading ('label', 'including_self_weight', 'wd', 'wl') VALUES ('Residential', 'false', '200.0', '300.0')")
        database.execSQL("INSERT INTO loading ('label', 'including_self_weight', 'wd', 'wl') VALUES ('Roof',        'false', '300.0', '150.0')")
    }
}