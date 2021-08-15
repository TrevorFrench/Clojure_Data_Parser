(ns main.core
  (:gen-class)
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn copy-csv [from to]
  (with-open [reader (io/reader from)
              writer (io/writer to)]
    (->> (csv/read-csv reader)
         (map #(rest (butlast %)))
         (csv/write-csv writer))))

(println "Program Started")

(with-open [reader (io/reader "in-file.csv")]
  (doall
    (csv/read-csv reader)
    (copy-csv "in-file.csv" "out-file.csv")
    ))

(println "Reader Complete")

;;(with-open [writer (io/writer "out-file.csv")]
;;  (csv/write-csv writer
;;                 [["abc" "def"]
;;                  ["ghi" "jkl"]]))

(println "Writer Complete")