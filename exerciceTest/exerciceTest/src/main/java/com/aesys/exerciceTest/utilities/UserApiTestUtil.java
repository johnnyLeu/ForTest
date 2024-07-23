package com.aesys.exerciceTest.utilities;

import com.aesys.exerciceTest.model.Address;
import com.aesys.exerciceTest.model.Company;
import com.aesys.exerciceTest.model.Geo;
import com.aesys.exerciceTest.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserApiTestUtil {
    public static User userToFind() {
        User newUser = new User();
        newUser.setId(4);
        newUser.setName("Patricia Lebsack");
        newUser.setUsername("Karianne");
        newUser.setEmail("Julianne.OConner@kory.org");

        Address address = new Address();
        address.setStreet("Hoeger Mall");
        address.setSuite("Apt. 692");
        address.setCity("South Elvis");
        address.setZipcode("53919-4257");

        Geo geo = new Geo();
        geo.setLat("29.4572");
        geo.setLng("-164.2990");
        address.setGeo(geo);

        newUser.setAddressObject(address);
        newUser.setPhone("493-170-9623 x156");
        newUser.setWebsite("kale.biz");

        Company company = new Company();
        company.setName("Robel-Corkery");
        company.setCatchPhrase("Multi-tiered zero tolerance productivity");
        company.setBs("transition cutting-edge web services");
        newUser.setCompanyObject(company);
        return newUser;
    }

    public static User notExistingUser() {
        User newUser = new User();
        newUser.setName("Dwayne Johnson");
        newUser.setUsername("the ROCK");
        newUser.setEmail("rock@gmail.com");

        Address address = new Address();
        address.setStreet("Mall");
        address.setSuite("Apt. 92");
        address.setCity("South park");
        address.setZipcode("53918");

        Geo geo = new Geo();
        geo.setLat("29.4579");
        geo.setLng("-14.2990");
        address.setGeo(geo);

        newUser.setAddressObject(address);
        newUser.setPhone("493-170-9623-677");
        newUser.setWebsite("rock.com");

        Company company = new Company();
        company.setName("wwa");
        company.setCatchPhrase("WEIGTH");
        company.setBs("WEIGTH");
        newUser.setCompanyObject(company);
        return newUser;
    }

    public static User getNewUser(int id, Company companyObject, String website, String phone, Address addressObject, Geo geoObject, String email, String username, String name) {
        Company company = new Company(companyObject.getName(), companyObject.getCatchPhrase(), companyObject.getBs());
        Geo geo = new Geo(geoObject.getLat(), geoObject.getLng());
        Address address = new Address(addressObject.getStreet(), addressObject.getSuite(), addressObject.getCity(), addressObject.getZipcode(), geo);

        return new User(id, company, website, phone, address, email, username, name);
    }

    public static void verifyUserDetails(User user, int expectedId, String expectedName, String expectedUsername, String expectedEmail, String expectedPhone, String expectedWebsite) {
        assertEquals(expectedId, user.getId(), "L'ID dell'utente non corrisponde");
        assertEquals(expectedName, user.getName(), "Il nome dell'utente non corrisponde");
        assertEquals(expectedUsername, user.getUsername(), "Il nome utente dell'utente non corrisponde");
        assertEquals(expectedEmail, user.getEmail(), "L'email dell'utente non corrisponde");
        assertEquals(expectedPhone, user.getPhone(), "Il numero di telefono dell' utente non corrisponde");
        assertEquals(expectedWebsite, user.getWebsite(), "Il sito web dell' utente non corrisponde");
    }

    public static void verifyUserDetailsNoId(User user, String expectedName, String expectedUsername, String expectedEmail, String expectedPhone, String expectedWebsite) {
        assertEquals(expectedName, user.getName(), "Il nome dell'utente non corrisponde");
        assertEquals(expectedUsername, user.getUsername(), "Il nome utente dell'utente non corrisponde");
        assertEquals(expectedEmail, user.getEmail(), "L'email dell'utente non corrisponde");
        assertEquals(expectedPhone, user.getPhone(), "Il numero di telefono dell' utente non corrisponde");
        assertEquals(expectedWebsite, user.getWebsite(), "Il sito web dell' utente non corrisponde");
    }

    public static void verifyAddress(Address actualAddress, Address expectedAddress) {
        assertEquals(expectedAddress.getStreet(), actualAddress.getStreet(), "La via dell'indirizzo non corrisponde");
        assertEquals(expectedAddress.getSuite(), actualAddress.getSuite(), "La suite dell'indirizzo non corrisponde");
        assertEquals(expectedAddress.getCity(), actualAddress.getCity(), "La città dell'indirizzo non corrisponde");
        assertEquals(expectedAddress.getZipcode(), actualAddress.getZipcode(), "Il CAP dell'indirizzo non corrisponde");
        assertEquals(expectedAddress.getGeo().getLat(), actualAddress.getGeo().getLat(), "La latitudine dell'indirizzo non corrisponde");
        assertEquals(expectedAddress.getGeo().getLng(), actualAddress.getGeo().getLng(), "La longitudine dell'indirizzo non corrisponde");
    }

    public static void verifyCompany(Company actualCompany, Company expectedCompany) {
        assertEquals(expectedCompany.getName(), actualCompany.getName(), "Il nome dell'azienda non corrisponde");
        assertEquals(expectedCompany.getCatchPhrase(), actualCompany.getCatchPhrase(), "Il motto dell'azienda non corrisponde");
        assertEquals(expectedCompany.getBs(), actualCompany.getBs(), "Il business dell'azienda non corrisponde");
    }
}
