(ns jarvis.plugins.health-report
  (:require [clj-jenkins.job :as job]
            [clojure.string :as s]))

(defn ^{:description "Prints the health report for the specified job."
        :command "health-report"
        :params "job"
        :usage "~health-report MyJob"
        :author "Jonathan Chauncey"
        :plugin true}
  health-report [message content-vec]
  (-> message
    (get "content")
    (s/split #"\s")
    (nth 1)
    (job/health-report)))
