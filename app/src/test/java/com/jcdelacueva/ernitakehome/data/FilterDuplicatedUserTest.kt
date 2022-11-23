package com.jcdelacueva.ernitakehome.data

import com.jcdelacueva.ernitakehome.data.model.User
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class FilterDuplicatedUserTest {

    @Test
    fun testFilterDuplicates() {
        val duplicatedUser = User(
            "1",
            "John",
            "https://www.alchinlong.com/wp-content/uploads/2015/09/sample-profile.png"
        )

        val usersList = mutableListOf<User>()

        for (i in 2..10 + 1) {
            usersList.add(
                User(
                    i.toString(),
                    "User#${i}",
                    "https://www.alchinlong.com/wp-content/uploads/2015/09/sample-profile.png"
                )
            )
        }

        val distinctUsersCount = usersList.size

        val duplicatedDataCount = 5
        for (i in 1..duplicatedDataCount) {
            usersList.add(duplicatedUser)
        }

        val notDistinctUsersCount = usersList.size

        assertEquals(notDistinctUsersCount, distinctUsersCount + duplicatedDataCount)

        val uniqueUsers = filterDuplicatedUser(usersList)
        assertTrue(uniqueUsers.size < usersList.size)
        assertTrue(uniqueUsers.size == distinctUsersCount + 1)
    }
}