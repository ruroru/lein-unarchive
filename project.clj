(defproject org.clojars.jj/unarchive "1.0.5-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.apache.commons/commons-compress "1.27.1" :exclusions [commons-io/commons-io]]
                 [commons-io/commons-io "2.15.1"]
                 [org.clojars.jj/surykatka "1.0.0"]]
  :plugins [[org.clojars.jj/unarchive "1.0.5-SNAPSHOT"]]
  :unarchive [{:source "./pom.xml.tar.gz"}]
  :repl-options {:init-ns unarchive.core})
