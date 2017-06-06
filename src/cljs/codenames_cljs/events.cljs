(ns codenames-cljs.events
  (:require [re-frame.core :as re-frame]
            [codenames-cljs.db :as db]
            [codenames-cljs.moves :as moves]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :move
 (fn  [db [_ word]]
   (moves/move! (:game db/default-db) word)))
