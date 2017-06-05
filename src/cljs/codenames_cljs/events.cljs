(ns codenames-cljs.events
    (:require [re-frame.core :as re-frame]
              [codenames-cljs.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
