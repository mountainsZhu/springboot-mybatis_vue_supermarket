import Vue from 'vue'
import VueRouter from 'vue-router'

import ProductManage from '../views/product/ProductManage.vue';
import ProviderManage from "../views/product/ProviderManage";
import InstoreManage from "../views/product/InstoreManage";
import OutstoreManage from "../views/product/OutstoreManage";
import UpdateProduct from "../views/product/UpdateProduct";
import UpdateProvider from "../views/product/UpdateProvider";

import SaleManage from "../views/sale/SaleManage";
import UpDownSaleManage from "../views/sale/UpDownSaleManage";

import badStoreManage from "../views/store/badStoreManage";
import LowerManage from "../views/store/LowerManage";
import UpperManage from "../views/store/UpperManage";

import InstoreExcel from "../views/excel/InstoreExcel";
import MonthExcel from "../views/excel/MonthExcel";
import OutstoreExcel from "../views/excel/OutstoreExcel";
import StoreExcel from "../views/excel/StoreExcel";
import UplowExcel from "../views/excel/UplowExcel";
import YearExcel from "../views/excel/YearExcel";

import BasicUser from "../views/user/BasicUser";
import Index from "../views/Index";
import Login from "../views/user/Login";
import Regist from "../views/user/Regist";
import UpdatePassword from "../views/user/UpdatePassword";

Vue.use(VueRouter)

const routes = [
  {
    path: "/index",
    name: "商品管理",
    show: true,
    component: Index,
    children: [
      {
        path: "/productManage",
        name: "商品管理",
        component: ProductManage
      },
      {
        path: "/providerManage",
        name: "供应商管理",
        component: ProviderManage
      },
      {
        path: "/instoreManage",
        name: "进货管理",
        component: InstoreManage
      },
      {
        path: "/outstoreManage",
        name: "退货管理",
        component: OutstoreManage
      }
    ]
  },
  {
    path: "/index",
    name: "销售管理",
    show: true,
    component: Index,
    children: [
      {
        path: "/saleManage",
        name: "收银台管理",
        component: SaleManage
      },
      {
        path: "/upsaleManage",
        name: "上/下架管理",
        component: UpDownSaleManage
      }
    ]
  },
  {
    path: "/index",
    name: "库存管理",
    show: true,
    component: Index,
    children: [
      {
        path: "/badstoreManage",
        name: "变质/损坏处理",
        component: badStoreManage
      },
      {
        path: "/UpperManage",
        name: "积压警告",
        component: UpperManage
      },
      {
        path: "/LowerManage",
        name: "缺货警告",
        component: LowerManage
      }
    ]
  },
  {
    path: "/index",
    name: "报表管理",
    show: true,
    component: Index,
    children: [
      {
        path: "/monthExcel",
        name: "月销售报表",
        component: MonthExcel
      },
      {
        path: "/yearExcel",
        name: "年销售报表",
        component: YearExcel
      },
      {
        path: "/instoreExcel",
        name: "进货报表",
        component: InstoreExcel
      },
      {
        path: "/outstoreExcel",
        name: "退货报表",
        component: OutstoreExcel
      },
      {
        path: "/storeExcel",
        name: "库存报表",
        component: StoreExcel
      },
      {
        path: "/uplowExcel",
        name: "积压/缺货报表",
        component: UplowExcel
      }
    ]
  },
  {
    path: "/index",
    name: "用户管理",
    show: true,
    component: Index,
    children: [
      {
        path: "/basicUser",
        name: "基本用户信息",
        component: BasicUser
      }
    ]
  },
  {
    path: "/index",
    show: false,
    component: Index,
    children: [
      {
        path: "/updateproduct",
        name: "修改商品信息",
        component: UpdateProduct
      },
      {
        path: "/updateProvider",
        name: "修改供应商信息",
        component: UpdateProvider
      }
    ]
  },
  {
    path: "/",
    show: false,
    component: Login
  },
  {
    path: "/regist",
    show: false,
    component: Regist
  },
  {
    path: "/updatepassword",
    show: false,
    component: UpdatePassword
  }
]

const router = new VueRouter({
  mode: 'history',
  base:process.env.BASE_URL,
  routes
})

export default router
