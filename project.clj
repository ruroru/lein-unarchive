(defproject org.clojars.jj/unarchive "1.0.1-SNAPSHOT"
  :description "A Leiningen plugin to extract tar.gz and zip archives"
  :url "https://github.com/ruroru/lein-unarchive"
  :license {:name "EPL-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}

  :deploy-repositories [["clojars" {:url      "https://repo.clojars.org"
                                    :username :env/clojars_user
                                    :password :env/clojars_pass}]]

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.apache.commons/commons-compress "1.25.0"]
                 [commons-io/commons-io "2.19.0"]
                 [org.clojars.jj/surykatka "1.0.0"]]
  :plugins [[org.clojars.jj/bump "1.0.4"]
            [org.clojars.jj/bump-md "1.0.0"]
            [org.clojars.jj/strict-check "1.0.2"]]

  :repl-options {:init-ns unarchive.core})
