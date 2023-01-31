// package com.musicShare.musicShare.entities;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;

// public class AccountTests {

// @Test
// public void testTheConstructor() {
// String email = "testAccount@Account.com";
// String password = "password";
// Account account = new Account(email, password);
// assertEquals(account.getEmail(), email);
// assertEquals(account.getPassword(), password);
// assertFalse(account.isEnabled());
// }

// @Test
// public void testGetId() {
// Account Account = new Account();
// Account.setId(1L);
// assertEquals(1L, Account.getId().longValue());
// }

// @Test
// public void testSetId() {
// Account Account = new Account();
// Account.setId(1L);
// assertEquals(1L, Account.getId().longValue());
// }

// @Test
// public void testGetAccountname() {
// Account Account = new Account();
// String email = "testAccount@Account.com";
// Account.setEmail(email);
// assertEquals(email, Account.getEmail());
// }

// @Test
// public void testSetAccountname() {
// Account Account = new Account();
// String email = "testAccount@Account.com";
// Account.setEmail(email);
// assertEquals(email, Account.getEmail());
// }

// @Test
// public void testGetPassword() {
// Account Account = new Account();
// Account.setPassword("password123");
// assertEquals("password123", Account.getPassword());
// }

// @Test
// public void testSetPassword() {
// Account Account = new Account();
// Account.setPassword("password123");
// assertEquals("password123", Account.getPassword());
// }

// @Test
// public void testIsEnabled() {
// Account Account = new Account();
// Account.setEnabled(true);
// assertTrue(Account.isEnabled());
// }

// @Test
// public void testSetEnabled() {
// Account Account = new Account();
// Account.setEnabled(true);
// assertTrue(Account.isEnabled());
// }
// }