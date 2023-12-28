package ajman.shayan.joisty.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration4To3 : Migration(4, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE loading RENAME COLUMN loading_id TO id;")
    }
}