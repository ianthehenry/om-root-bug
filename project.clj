(defproject update-bug "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2280"]
                 [om "0.7.1"]
                 [org.clojure/core.async "0.1.319.0-6b1aca-alpha"]
                 ]
  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:output-to "out/main.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map "out/main.js.map"}}
                       ]})
