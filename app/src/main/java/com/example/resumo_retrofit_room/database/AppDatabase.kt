package com.example.resumo_retrofit_room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.resumo_retrofit_room.dao.GithubDao
import com.example.resumo_retrofit_room.model.GithubModel
import com.example.resumo_retrofit_room.model.GithubOwner


/**
 * Classe abstrata para criar nosso database local
 * Devemos passar na annotation @Database os parametros:
 * @param entities Array com a lista de classes com a annotation @Entity
 * @param version Int versao do banco (Quando qualquer coisa for alterada em relaçao
 * ao banco este numero deve ser incrementado)
 */


@Database(
    entities = [
        GithubModel::class,
        GithubOwner::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

   //funcao declarada para o Room implementar automaticamente nosso DAO
    abstract fun getGithubDao(): GithubDao

    companion object {

       //Singleton que irá gerar nossa classe AppDatabse com tudo implementado pelo Room.
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java, "github_app_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}