import angular from 'angular';
import Home from './home/home';
import About from './about/about';
import Accounts from './accounts/accounts';
import Locations from './locations/locations';
import LocationTypes from './locationTypes/locationTypes';
import Categories from './categories/categories';

let componentModule = angular.module('app.components', [
  Home,
  About,
  Accounts,
  Locations,
  LocationTypes,
  Categories
])

.name;

export default componentModule;
