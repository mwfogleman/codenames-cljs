(ns codenames-cljs.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :game
 (fn [db]
   (:game db)))

(re-frame/reg-sub
 :cell
 (fn [db [_ x y]]
   (reaction (get-in db [:board [x y]]))))
