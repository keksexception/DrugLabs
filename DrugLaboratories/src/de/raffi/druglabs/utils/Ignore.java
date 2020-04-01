package de.raffi.druglabs.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * used to tag a ItemStack which should be ignored
 * by generating the <b>admin item inventory</b>
 * @see InventoryManager#getItemInventory()
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Ignore {}
