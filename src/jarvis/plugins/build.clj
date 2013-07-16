(ns jarvis.plugins.build
  (:require [jarvis.jenkins.util :as util]
            [clj-jenkins.job :as job]
            [clojure.string :as s]))

(defn ^{:description "build <pipeline> - Trigger build pipeline (which may cause a deployment) for specified service."
        :command "build"
        :author "Jonathan Chauncey"
        :plugin true}
  build [message content-vec]
  (let [job (-> message
            (get "content")
            (s/split #"\s")
            (nth 1))]
    (job/trigger-build job)
    (str "Triggering build for: [" job "]")))