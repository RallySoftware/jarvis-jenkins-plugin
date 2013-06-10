(ns jarvis.plugins.status
  (:require [jarvis.jenkins.util :as util]
            [clj-jenkins.pipeline :as pipeline]
            [clj-jenkins.job :as job]
            [clojure.string :as s]))

(declare get-job get-jobs)

(defn ^{:description "status <pipeline> - Determines the status of all downstream jobs from the specified job."
        :command "status"
        :author "Jonathan Chauncey and Matt Farrar"
        :plugin true}
  status [message]
  (let [job (get-job message)
        jobs (get-jobs job)
        described-jobs (map #(util/info (% :name ) (get-in % [:lastBuild :number ])) jobs)]
    (str "Build Statuses for all downstream jobs of: " job (s/join "\n" described-jobs))))

(defn get-jobs [job]
  (-> job
    (pipeline/status)
    (get-in [job :jobs])))

(defn- get-job [message]
  (-> message
    (get "content")
    (s/split #"\s")
    (nth 1)))