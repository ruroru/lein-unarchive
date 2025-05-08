(ns leiningen.unarchive-test
  (:require [clojure.test :refer [deftest use-fixtures is]]
            [leiningen.unarchive :as unarchive])

  (:import (java.io File)
           (java.nio.file FileVisitResult Files Paths SimpleFileVisitor)))

(def target-dir "./target/folder")

(defn- delete-recursively [directory]
  (let [path (Paths/get ^String directory (into-array String []))]
    (Files/walkFileTree
      path
      (proxy [SimpleFileVisitor] []
        (visitFile [file _]
          (Files/delete file)
          FileVisitResult/CONTINUE)
        (postVisitDirectory [dir _]
          (Files/delete dir)
          FileVisitResult/CONTINUE)))))


(use-fixtures :each (fn [f]
                      (when (.exists (File. ^String target-dir))
                        (delete-recursively target-dir))
                      (f)))


(defn- verify-expected-files-exist []
  (let [file1 (File. "target/folder/file.txt")
        file2 (File. "target/folder/folder/folder/file.txt")]
    (is (.exists file1))
    (is (.exists file2))))


(defn- verify-files-do-not-exist []
  (let [file1 (File. "target/folder/")]
    (is (= false (.exists file1)))))


(deftest should-unarchive-zip
  (let [project {:unarchive [{:source "test/resources/folder.zip"}]}]
    (unarchive/unarchive project)
    (verify-expected-files-exist)))


(deftest does-nothing-when-not-an-archive
  (let [project {:unarchive [{:source "test/resources/folder/file.txt"}]}]
    (unarchive/unarchive project)
    (verify-files-do-not-exist)))


(deftest should-unarchive-gzip
  (let [project {:unarchive [{:source "test/resources/folder.tar.gz"}]}]
    (unarchive/unarchive project)
    (verify-expected-files-exist)))
