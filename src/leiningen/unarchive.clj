(ns leiningen.unarchive
  (:require [jj.surykatka :as surykatka])
  (:import (java.io BufferedOutputStream File FileInputStream FileOutputStream)
           (org.apache.commons.compress.archivers.tar TarArchiveInputStream)
           (org.apache.commons.compress.archivers.zip ZipArchiveInputStream)
           (org.apache.commons.compress.compressors.gzip GzipCompressorInputStream)))


(defmulti extract (fn [^String input-file-path]
                    (let [buffer (byte-array 20)]
                      (with-open [fis (FileInputStream. input-file-path)]
                        (.read fis buffer)
                        (surykatka/get-file-type buffer)))))


(defmethod extract :zip [^String input-file-path]
  (with-open [input-stream (FileInputStream. input-file-path)
              archive-input (ZipArchiveInputStream. input-stream)]
    (loop []
      (when-let [entry (.getNextEntry archive-input)]
        (when (not (.isDirectory entry))
          (let [out-file (File. (str "./target/" (.getName entry)))]
            (.mkdirs (.getParentFile out-file))
            (with-open [out-stream (BufferedOutputStream. (FileOutputStream. out-file))]
              (.transferTo archive-input out-stream))))
        (recur)))))


(defmethod extract :gzip [^String input-file-path]
  (with-open [file-stream (FileInputStream. input-file-path)
              gzip-stream (GzipCompressorInputStream. file-stream)
              archive-input (TarArchiveInputStream. gzip-stream)]
    (loop []
      (when-let [entry (.getNextEntry archive-input)]
        (when (and (not (.isDirectory entry))
                   (not (nil? (.getName entry))))
          (let [out-file (File. (str "./target/" (.getName entry)))]
            (.mkdirs (.getParentFile out-file))
            (with-open [out-stream (BufferedOutputStream. (FileOutputStream. out-file))]
              (.transferTo archive-input out-stream))))
        (recur)))))


(defmethod extract :default [^String input-file-path]
  (println (format "%s is not supported" input-file-path)))


(defn unarchive
  [project & _]
  (doseq [{:keys [source]} (:unarchive project)]
    (extract source)))