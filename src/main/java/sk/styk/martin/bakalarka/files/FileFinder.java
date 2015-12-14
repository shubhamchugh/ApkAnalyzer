package sk.styk.martin.bakalarka.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin Styk on 23.11.2015.
 */
public class FileFinder {

    private List<File> apkFolders;
    private List<File> files = new ArrayList<File>();

    public FileFinder(File folder) {
        if (folder == null) {
            throw new NullPointerException("folder null");
        }
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not valid directory");
        }
        this.apkFolders = new ArrayList<File>();
        this.apkFolders.add(folder);
    }

    public FileFinder(File... folders) {
        this.apkFolders = new ArrayList<File>();
        for (File folder : folders) {
            if (folder == null) {
                throw new NullPointerException("folder null");
            }
            if (!folder.isDirectory()) {
                throw new IllegalArgumentException("folder is not valid directory" + folder.getAbsolutePath());
            }
            this.apkFolders.add(folder);
        }
    }

    public FileFinder(List<File> folders) {
        this.apkFolders = new ArrayList<File>();
        for (File folder : folders) {
            if (folder == null) {
                throw new NullPointerException("folder null");
            }
            if (!folder.isDirectory()) {
                throw new IllegalArgumentException("folder is not valid directory" + folder.getAbsolutePath());
            }
            this.apkFolders.add(folder);
        }
    }

    public List<ApkFile> getApkFilesInDirectories() {
        files = new ArrayList<File>();
        for (File directory : apkFolders) {
            getFilesInDirectoryFileTypeMatch(directory, "apk");
        }
        List<ApkFile> apkFiles = new ArrayList<ApkFile>();
        for (File f : files) {
            apkFiles.add(new ApkFile(f.getAbsolutePath()));
        }
        return apkFiles;
    }

    private void getFilesInDirectoryFileTypeMatch(File directory, String... typeFilter) {
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                for (String type : typeFilter) {
                    if (file.getName().endsWith(type))
                        files.add(file);
                    //break;
                }
            } else if (file.isDirectory()) {
                getFilesInDirectoryFileTypeMatch(new File(file.getAbsolutePath()), typeFilter);
            }
        }
    }
}
