(ns ivy.routes.home
  (:use compojure.core)
  (:require [ivy.views.layout :as layout]
            [ivy.util :as util]
            [postal.core :as postal]
            [noir.response :as resp]))

(defn student-page []
  (layout/render
    "student.html" {:content (util/md->html "/md/docs.md")}))

(defn home-page []
  (layout/render
    "corporate.html"))

(defn about-page []
  (layout/render "about.html"))

(defn handle-contact [email subject message]
  (postal/send-message ^{:host "smtp.gmail.com"
                         :user "mwilkison"
                         :pass "maclane1"
                         :ssl true}
                       {:from email
                        :to "mwilkison@gmail.com"
                        :subject subject
                        :body (str "From: " email "\n" message)})
  (resp/redirect "/"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/students" [] (student-page))
  (POST "/students" [] (student-page))
  (GET "/about" [] (about-page))
  (POST "/contact" [email subject message]
        (handle-contact email subject message)))
