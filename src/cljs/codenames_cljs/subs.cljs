(ns codenames-cljs.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [com.rpl.specter :as S]))

(defn cell-filterer [[x y :as target] {:keys [position]}]
  (= target position))

(defn get-cell-word [game x y]
  (S/select-any [S/ATOM :game :words (S/filterer #(cell-filterer [x y] %)) S/ALL :word]
                game))

(re-frame/reg-sub
 :game
 :game)

(re-frame/reg-sub
 :cell
 (fn [db [_ x y]]
   [:span (str (get-cell-word db x y))]))
