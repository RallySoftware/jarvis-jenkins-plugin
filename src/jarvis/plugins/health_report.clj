(ns jarvis.plugins.health-report
  (:require [clj-jenkins.job :as job]
            [clojure.string :as s]))

(defn ^{:description "health-report <job> - Prints the health report for the specified job."
        :command "health-report"
        :author "Jonathan Chauncey"
        :plugin true}
  health-report [message]
  (-> message
    (get "content")
    (s/split #"\s")
    (nth 1)
    (job/health-report)))