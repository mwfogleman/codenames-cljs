(ns codenames-cljs.db
  (:require [codenames-cljs.game :as game]))

(def default-db
  {:game (game/prepare-game)})
