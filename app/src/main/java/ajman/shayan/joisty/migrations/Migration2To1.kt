package ajman.shayan.joisty.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2To1 : Migration(2, 1) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("BEGIN TRANSACTION;")
        database.execSQL("DROP INDEX index_joist_design_loading_id;")
        database.execSQL("ALTER TABLE joist_design DROP COLUMN loading_id;")
        database.execSQL("ALTER TABLE joist_design DROP COLUMN updated_at;")
        database.execSQL("DROP TABLE loading;")
        database.execSQL("COMMIT;")
    }
}