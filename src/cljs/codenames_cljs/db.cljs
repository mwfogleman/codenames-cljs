(ns codenames-cljs.db
  (:require [codenames-cljs.game :as game]))

(def default-db
  {:board {}
   ;; :turn :x
   :game (game/prepare-game)})
