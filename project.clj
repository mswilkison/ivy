(defproject
  ivy
  "0.1.0-SNAPSHOT"
  :repl-options
  {:init-ns ivy.repl}
  :dependencies
  [[ring-server "0.3.1"]
   [domina "1.0.2"]
   [environ "0.4.0"]
   [markdown-clj "0.9.41"]
   [com.taoensso/timbre "2.7.1"]
   [prismatic/dommy "0.1.2"]
   [korma "0.3.0-RC6"]
   [org.clojure/clojurescript "0.0-2127"]
   [http-kit "2.1.13"]
   [com.taoensso/tower "2.0.1"]
   [org.clojure/clojure "1.5.1"]
   [cljs-ajax "0.2.2"]
   [log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [compojure "1.1.6"]
   [selmer "0.5.9"]
   [lib-noir "0.7.9"]
   [com.postspectacular/rotor "0.1.0"]
   [postgresql/postgresql "9.1-901.jdbc4"]]
  :cljsbuild
  {:builds
   [{:source-paths ["src-cljs"],
     :compiler
     {:pretty-print false,
      :output-to "resources/public/js/site.js",
      :optimizations :advanced}}]}
  :ring
  {:handler ivy.handler/app,
   :init ivy.handler/init,
   :destroy ivy.handler/destroy}
  :profiles
  {:uberjar {:aot :all},
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]],
    :env {:selmer-dev true}}}
  :url
  "http://example.com/FIXME"
  :main
  ivy.core
  :plugins
  [[lein-ring "0.8.10"]
   [lein-environ "0.4.0"]
   [lein-cljsbuild "0.3.3"]]
  :description
  "FIXME: write description"
  :min-lein-version "2.0.0")