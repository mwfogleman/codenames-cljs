(ns codenames-cljs.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [com.rpl.specter :as S]))

(defn cell-filterer [[x y :as target] {:keys [position]}]
  (= target position))

(defn get-cell [game x y]
  (S/select-any [:game S/ATOM :words (S/filterer #(cell-filterer [x y] %)) S/ALL] game))

(re-frame/reg-sub
 :game
 :game)

(re-frame/reg-sub
 :cell
 (fn [db [_ x y]]
   (get-cell db x y)))
