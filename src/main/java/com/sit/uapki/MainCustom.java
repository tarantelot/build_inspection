package com.sit.uapki;

//import com.sit.uapki.Library;
import com.sit.uapki.method.Providers;
import com.sit.uapki.method.Storages;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainCustom {
    public static void main(String[] args) {
        try {
            new MainCustom().providers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void providers() throws Exception  {
        Library lib = new Library(true);
        System.out.println("Library loaded. Name: " + lib.getName() + ", Version: " + lib.getVersion());
        System.out.println("Бібліотека завантажена (тест utf-8). Назва: " + lib.getName() + ", Версія: " + lib.getVersion());

        final List<String> list_providernames = new LinkedList<>();
        final List<Providers.CmProviderInfo> list_providerinfos = lib.getProviders();
        for (Providers.CmProviderInfo provider : list_providerinfos) {
            System.out.println("Key provider info: id = " + provider.getId() + "; version = " + provider.getVersion()
                    + "; description = " + provider.getDescription() + "; manufacturer = " + provider.getManufacturer());
            list_providernames.add(provider.getId());

            // Якщо провайдер підтримує отримання переліку доступних сховищ ключів - отримуємо переік сховищ
            if (provider.isSupportListStorages() == true) {
                final ArrayList<Storages.StorageInfo> storages_info = lib.getStorages(provider.getId());

                // Для кожного сховища отримуємо перелік ключів
                for (Storages.StorageInfo storageInfo : storages_info) {
                    System.out.println("Key storage info: id = " + storageInfo.getId() + "; description = " + storageInfo.getDescription()
                            + "; label = " + storageInfo.getLabel() + "; serial = " + storageInfo.getSerial() );
                }
                System.out.println("");
            }
        }

        System.out.println("List provider names: " + list_providernames);
    }


}
