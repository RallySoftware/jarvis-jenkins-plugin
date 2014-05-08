(ns jarvis.plugins.last-successful-build
  (:require [jarvis.jenkins.util :as util]
            [clj-jenkins.job :as job]
            [clojure.string :as s]))

(defn ^{:description "Prints the last successful build for the specified job."
        :command "last-successful-build"
        :params "job"
        :usage "~last-successful-build MyJob"
        :author "Jonathan Chauncey Matt Farrar"
        :plugin true}
  last-successful-build [message content-vec]
  (-> message
    (get "content")
    (s/split #"\s")
    (nth 1)
    (job/last-successful-build)
    (util/info)))
