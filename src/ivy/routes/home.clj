(ns ivy.routes.home
  (:use compojure.core)
  (:require [ivy.views.layout :as layout]
            [ivy.util :as util]
            [postal.core :as postal]
            [noir.response :as resp]
            [clj-stripe.common :as common]
            [clj-stripe.charges :as charges]))

(defn charge [& params]
  (println (str "WHY DOESN't WORK" (:stripeToken (first params))))
  (common/with-token "sk_live_xN4G3Xg7EaRH8NwXCRA6oKyr"
    (common/execute (charges/create-charge (common/money-quantity 10000 "usd")
                                           (common/card (:stripeToken (first params) "broke")))))
  (resp/redirect "/students"))

(defn student-page [& params]
  (layout/render
    "student.html" {:params (:stripeToken (first params))}))

(defn home-page []
  (layout/render
    "corporate.html"))

(defn about-page []
  (layout/render "about.html"))

(defn demo-page []
  (layout/render "ivy-demo.html"))

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
  (GET "/demo" [] (demo-page))
  (GET "/students" [] (student-page))
  (POST "/students" [& params] (student-page params))
  (POST "/charge" [& params] (charge params))
  (GET "/about" [] (about-page))
  (POST "/contact" [email subject message]
        (handle-contact email subject message)))
