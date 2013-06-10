(ns jarvis.jenkins.util
  (:require [clj-jenkins.build :as build]))

(defn build->sha [build]
  (-> build
    (get-in ["actions"])
    (->>
      (map #(get-in % ["lastBuiltRevision" "SHA1"]))
      (remove nil?)
      first)))

(defn info
  ([job build]
    (when job
      (info (build/get-build job build))))
  ([json-build]
    (str
      "\n\tBuild: " (json-build "fullDisplayName")
      "\n\tStatus: " (json-build "result")
      "\n\tSHA: " (build->sha json-build))))