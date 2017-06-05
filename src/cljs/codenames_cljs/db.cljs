(ns codenames-cljs.db
  (:require [codenames-cljs.game :as game]))

(def default-db
  {:name "re-frame"
   :game (game/prepare-game)})
