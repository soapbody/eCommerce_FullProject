--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.2

-- Started on 2023-06-25 19:39:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3535 (class 1262 OID 17018)
-- Name: eCommerceFullProject; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "eCommerceFullProject" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE "eCommerceFullProject" OWNER TO postgres;

\connect "eCommerceFullProject"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 254 (class 1255 OID 17428)
-- Name: validachavepessoa(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE existe INTEGER;

BEGIN
	existe = (SELECT COUNT(1) FROM individual WHERE id = NEW.person_id);
	if (existe <= 0) then
		existe = (select count(1) from company where id = NEW.person_id);
	if (existe <= 0) then
		RAISE EXCEPTION 'Não foi encontrado o ID ou PK da pessoa para realizar a associação.';
	 	end if;
	end if;
	return new;
END;
$$;


ALTER FUNCTION public.validachavepessoa() OWNER TO postgres;

--
-- TOC entry 255 (class 1255 OID 17433)
-- Name: validachavepessoa2(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoa2() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE existe INTEGER;

BEGIN
	existe = (SELECT COUNT(1) FROM individual WHERE id = NEW.person_vendor_id);
	if (existe <= 0) then
		existe = (select count(1) from company where id = NEW.person_vendor_id);
	if (existe <= 0) then
		RAISE EXCEPTION 'Não foi encontrado o ID ou PK da pessoa para realizar a associação.';
	 	end if;
	end if;
	return new;
END;
$$;


ALTER FUNCTION public.validachavepessoa2() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 244 (class 1259 OID 17252)
-- Name: accounts_payable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts_payable (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    discount_value numeric(19,2),
    due_date date NOT NULL,
    payment_date date,
    total_value numeric(19,2) NOT NULL,
    person_id bigint NOT NULL,
    person_vendor_id bigint NOT NULL,
    status character varying(255) NOT NULL
);


ALTER TABLE public.accounts_payable OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17093)
-- Name: accounts_receivable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts_receivable (
    id bigint NOT NULL,
    person_id bigint NOT NULL,
    description character varying(255) NOT NULL,
    discount_value numeric(19,2),
    due_date date NOT NULL,
    payment_date date,
    status character varying(255) NOT NULL,
    total_value numeric(19,2) NOT NULL
);


ALTER TABLE public.accounts_receivable OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 17182)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id bigint NOT NULL,
    complement character varying(255),
    person_id bigint NOT NULL,
    address_type character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    neighborhood character varying(255) NOT NULL,
    postal_code character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    street character varying(255) NOT NULL
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 17401)
-- Name: company; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    category character varying(255),
    cnpj character varying(255) NOT NULL,
    company_name character varying(255) NOT NULL,
    municipal_registration character varying(255),
    state_registration character varying(255),
    trade_name character varying(255) NOT NULL
);


ALTER TABLE public.company OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17101)
-- Name: discount_coupon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discount_coupon (
    id bigint NOT NULL,
    porcent_value_discount integer,
    real_value_discount integer,
    discount_code character varying(255) NOT NULL,
    due_date_coupon date NOT NULL
);


ALTER TABLE public.discount_coupon OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 17408)
-- Name: individual; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.individual (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    cpf character varying(255) NOT NULL,
    date_of_birth date NOT NULL
);


ALTER TABLE public.individual OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 17139)
-- Name: invoice_product_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice_product_item (
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    purchase_invoice bigint NOT NULL,
    quantity double precision NOT NULL
);


ALTER TABLE public.invoice_product_item OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 17330)
-- Name: online_transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.online_transaction (
    id bigint NOT NULL,
    delivery_date date NOT NULL,
    delivery_day integer NOT NULL,
    discount_value numeric(19,2),
    sales_date date NOT NULL,
    shipping_cost numeric(19,2) NOT NULL,
    total_value numeric(19,2) NOT NULL,
    billing_address_id bigint NOT NULL,
    discount_coupon_id bigint,
    payment_method_id bigint NOT NULL,
    person_id bigint NOT NULL,
    sale_invoice_id bigint NOT NULL,
    shipping_address_id bigint NOT NULL
);


ALTER TABLE public.online_transaction OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17106)
-- Name: payment_method; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_method (
    id bigint NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.payment_method OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 17303)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    active boolean NOT NULL,
    click_quantity integer,
    depth double precision NOT NULL,
    description text NOT NULL,
    height double precision NOT NULL,
    link_youtube character varying(255),
    name character varying(255) NOT NULL,
    sale_value numeric(19,2) NOT NULL,
    stock_alert boolean,
    stock_alert_quantity integer,
    stock_quantity integer NOT NULL,
    unit_type character varying(255) NOT NULL,
    weight double precision NOT NULL,
    width double precision NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17019)
-- Name: product_brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_brand (
    id bigint NOT NULL,
    name_description character varying(255),
    description_name character varying(255) NOT NULL
);


ALTER TABLE public.product_brand OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17027)
-- Name: product_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_category (
    id bigint NOT NULL,
    description_name character varying(255) NOT NULL
);


ALTER TABLE public.product_category OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 17123)
-- Name: product_image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_image (
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    original_image text NOT NULL,
    thumbnail_image text NOT NULL
);


ALTER TABLE public.product_image OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 17415)
-- Name: product_rating; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_rating (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    score integer NOT NULL,
    person_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.product_rating OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 17259)
-- Name: purchase_invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase_invoice (
    id bigint NOT NULL,
    description character varying(255),
    discountl_value numeric(19,2),
    icms_value numeric(19,2) NOT NULL,
    invoice_number character varying(255) NOT NULL,
    purchase_date date NOT NULL,
    serial_number character varying(255) NOT NULL,
    total_value numeric(19,2) NOT NULL,
    accounts_payable_id bigint NOT NULL,
    person_id bigint NOT NULL
);


ALTER TABLE public.purchase_invoice OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 25210)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 17271)
-- Name: sale_invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sale_invoice (
    id bigint NOT NULL,
    number character varying(255) NOT NULL,
    pdf text NOT NULL,
    serial character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    xml text NOT NULL,
    online_transaction_id bigint NOT NULL
);


ALTER TABLE public.sale_invoice OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 17111)
-- Name: seq_accounts_payable; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_accounts_payable
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_accounts_payable OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17100)
-- Name: seq_accounts_receivable; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_accounts_receivable
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_accounts_receivable OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 17189)
-- Name: seq_address; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_address
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_address OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17112)
-- Name: seq_discount_coupon; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_discount_coupon
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_discount_coupon OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 17144)
-- Name: seq_invoice_product_item; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_invoice_product_item
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_invoice_product_item OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 17180)
-- Name: seq_online_transaction; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_online_transaction
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_online_transaction OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17113)
-- Name: seq_payment_method; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_payment_method
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_payment_method OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17053)
-- Name: seq_person; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_person
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_person OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 17121)
-- Name: seq_product; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17024)
-- Name: seq_product_brand; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_brand
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_brand OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17037)
-- Name: seq_product_category; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_category
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_category OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 17122)
-- Name: seq_product_image; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_image
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_image OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 17246)
-- Name: seq_product_rating; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_rating
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_rating OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 17138)
-- Name: seq_purchase_invoice; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_purchase_invoice
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_purchase_invoice OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17038)
-- Name: seq_role; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_role
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_role OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 17181)
-- Name: seq_sale_invoice; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_sale_invoice
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_sale_invoice OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 17230)
-- Name: seq_store_sale_item; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_store_sale_item
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_store_sale_item OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 17160)
-- Name: seq_tracking_status; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_tracking_status
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_tracking_status OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17068)
-- Name: seq_user; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_user OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 17225)
-- Name: store_sale_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.store_sale_item (
    id bigint NOT NULL,
    online_transaction_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity double precision NOT NULL
);


ALTER TABLE public.store_sale_item OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 17161)
-- Name: tracking_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tracking_status (
    id bigint NOT NULL,
    city character varying(255),
    distribution_center character varying(255),
    state character varying(255),
    status character varying(255),
    online_transaction_id bigint NOT NULL
);


ALTER TABLE public.tracking_status OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17061)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    role_id bigint NOT NULL,
    users_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 17335)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    current_password_date date NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    person_id bigint NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3520 (class 0 OID 17252)
-- Dependencies: 244
-- Data for Name: accounts_payable; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3498 (class 0 OID 17093)
-- Dependencies: 222
-- Data for Name: accounts_receivable; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3515 (class 0 OID 17182)
-- Dependencies: 239
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3526 (class 0 OID 17401)
-- Dependencies: 250
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3500 (class 0 OID 17101)
-- Dependencies: 224
-- Data for Name: discount_coupon; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3527 (class 0 OID 17408)
-- Dependencies: 251
-- Data for Name: individual; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.individual (id, email, name, phone, cpf, date_of_birth) VALUES (1, 'dnsafsufhu', 'pessoa fisica teste', '9933282847', '09281982943', '1998-10-10');


--
-- TOC entry 3509 (class 0 OID 17139)
-- Dependencies: 233
-- Data for Name: invoice_product_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3524 (class 0 OID 17330)
-- Dependencies: 248
-- Data for Name: online_transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3501 (class 0 OID 17106)
-- Dependencies: 225
-- Data for Name: payment_method; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3523 (class 0 OID 17303)
-- Dependencies: 247
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, active, click_quantity, depth, description, height, link_youtube, name, sale_value, stock_alert, stock_alert_quantity, stock_quantity, unit_type, weight, width) VALUES (1, true, 1, 50.2, 'produto teste', 80.5, 'asdsfgdgdfgdfg', 'nome produto teste', 50.00, true, 10, 1, 'UN', 60.7, 20);


--
-- TOC entry 3490 (class 0 OID 17019)
-- Dependencies: 214
-- Data for Name: product_brand; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3492 (class 0 OID 17027)
-- Dependencies: 216
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3507 (class 0 OID 17123)
-- Dependencies: 231
-- Data for Name: product_image; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3528 (class 0 OID 17415)
-- Dependencies: 252
-- Data for Name: product_rating; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product_rating (id, description, score, person_id, product_id) VALUES (1, 'teste avaliacao produto', 10, 1, 1);
INSERT INTO public.product_rating (id, description, score, person_id, product_id) VALUES (2, 'teste avaliacao produto', 10, 1, 1);


--
-- TOC entry 3521 (class 0 OID 17259)
-- Dependencies: 245
-- Data for Name: purchase_invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3529 (class 0 OID 25210)
-- Dependencies: 253
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, description) VALUES (56, 'ROLE_OBTER_ID');
INSERT INTO public.role (id, description) VALUES (58, 'ROLE_COMPRADOR');


--
-- TOC entry 3522 (class 0 OID 17271)
-- Dependencies: 246
-- Data for Name: sale_invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3517 (class 0 OID 17225)
-- Dependencies: 241
-- Data for Name: store_sale_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3512 (class 0 OID 17161)
-- Dependencies: 236
-- Data for Name: tracking_status; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3496 (class 0 OID 17061)
-- Dependencies: 220
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3525 (class 0 OID 17335)
-- Dependencies: 249
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 226
-- Name: seq_accounts_payable; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_accounts_payable', 1, false);


--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_accounts_receivable; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_accounts_receivable', 1, false);


--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 240
-- Name: seq_address; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_address', 1, false);


--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_discount_coupon; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_discount_coupon', 1, false);


--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 234
-- Name: seq_invoice_product_item; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_invoice_product_item', 1, false);


--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 237
-- Name: seq_online_transaction; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_online_transaction', 1, false);


--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 228
-- Name: seq_payment_method; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_payment_method', 1, false);


--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 219
-- Name: seq_person; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_person', 1, false);


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 229
-- Name: seq_product; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product', 1, false);


--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 215
-- Name: seq_product_brand; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_brand', 1, false);


--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_product_category; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_category', 1, false);


--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 230
-- Name: seq_product_image; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_image', 1, false);


--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 243
-- Name: seq_product_rating; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_rating', 1, false);


--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 232
-- Name: seq_purchase_invoice; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_purchase_invoice', 1, false);


--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 218
-- Name: seq_role; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_role', 62, true);


--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 238
-- Name: seq_sale_invoice; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_sale_invoice', 1, false);


--
-- TOC entry 3552 (class 0 OID 0)
-- Dependencies: 242
-- Name: seq_store_sale_item; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_store_sale_item', 1, false);


--
-- TOC entry 3553 (class 0 OID 0)
-- Dependencies: 235
-- Name: seq_tracking_status; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_tracking_status', 1, false);


--
-- TOC entry 3554 (class 0 OID 0)
-- Dependencies: 221
-- Name: seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_user', 1, false);


--
-- TOC entry 3298 (class 2606 OID 17258)
-- Name: accounts_payable accounts_payable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts_payable
    ADD CONSTRAINT accounts_payable_pkey PRIMARY KEY (id);


--
-- TOC entry 3282 (class 2606 OID 17099)
-- Name: accounts_receivable accounts_receivable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts_receivable
    ADD CONSTRAINT accounts_receivable_pkey PRIMARY KEY (id);


--
-- TOC entry 3294 (class 2606 OID 17188)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 3310 (class 2606 OID 17407)
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);


--
-- TOC entry 3284 (class 2606 OID 17105)
-- Name: discount_coupon discount_coupon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_coupon
    ADD CONSTRAINT discount_coupon_pkey PRIMARY KEY (id);


--
-- TOC entry 3312 (class 2606 OID 17414)
-- Name: individual individual_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.individual
    ADD CONSTRAINT individual_pkey PRIMARY KEY (id);


--
-- TOC entry 3290 (class 2606 OID 17143)
-- Name: invoice_product_item invoice_product_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_product_item
    ADD CONSTRAINT invoice_product_item_pkey PRIMARY KEY (id);


--
-- TOC entry 3306 (class 2606 OID 17334)
-- Name: online_transaction online_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.online_transaction
    ADD CONSTRAINT online_transaction_pkey PRIMARY KEY (id);


--
-- TOC entry 3286 (class 2606 OID 17110)
-- Name: payment_method payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_pkey PRIMARY KEY (id);


--
-- TOC entry 3274 (class 2606 OID 17023)
-- Name: product_brand product_brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_brand
    ADD CONSTRAINT product_brand_pkey PRIMARY KEY (id);


--
-- TOC entry 3276 (class 2606 OID 17031)
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- TOC entry 3288 (class 2606 OID 17129)
-- Name: product_image product_image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT product_image_pkey PRIMARY KEY (id);


--
-- TOC entry 3304 (class 2606 OID 17309)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3314 (class 2606 OID 17419)
-- Name: product_rating product_rating_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_rating
    ADD CONSTRAINT product_rating_pkey PRIMARY KEY (id);


--
-- TOC entry 3300 (class 2606 OID 17265)
-- Name: purchase_invoice purchase_invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_invoice
    ADD CONSTRAINT purchase_invoice_pkey PRIMARY KEY (id);


--
-- TOC entry 3316 (class 2606 OID 25214)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3302 (class 2606 OID 17277)
-- Name: sale_invoice sale_invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_invoice
    ADD CONSTRAINT sale_invoice_pkey PRIMARY KEY (id);


--
-- TOC entry 3296 (class 2606 OID 17229)
-- Name: store_sale_item store_sale_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store_sale_item
    ADD CONSTRAINT store_sale_item_pkey PRIMARY KEY (id);


--
-- TOC entry 3292 (class 2606 OID 17167)
-- Name: tracking_status tracking_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracking_status
    ADD CONSTRAINT tracking_status_pkey PRIMARY KEY (id);


--
-- TOC entry 3278 (class 2606 OID 17065)
-- Name: user_roles uk_5q4rc4fh1on6567qk69uesvyf; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT uk_5q4rc4fh1on6567qk69uesvyf UNIQUE (role_id);


--
-- TOC entry 3280 (class 2606 OID 17092)
-- Name: user_roles unique_role_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT unique_role_user UNIQUE (users_id, role_id);


--
-- TOC entry 3308 (class 2606 OID 17341)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3332 (class 2620 OID 17436)
-- Name: accounts_receivable validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.accounts_receivable FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3334 (class 2620 OID 17438)
-- Name: address validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.address FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3342 (class 2620 OID 17444)
-- Name: online_transaction validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.online_transaction FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3340 (class 2620 OID 17440)
-- Name: purchase_invoice validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.purchase_invoice FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3344 (class 2620 OID 17442)
-- Name: users validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.users FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3333 (class 2620 OID 17437)
-- Name: accounts_receivable validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.accounts_receivable FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3335 (class 2620 OID 17439)
-- Name: address validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.address FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3343 (class 2620 OID 17445)
-- Name: online_transaction validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.online_transaction FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3341 (class 2620 OID 17441)
-- Name: purchase_invoice validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.purchase_invoice FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3345 (class 2620 OID 17443)
-- Name: users validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.users FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3346 (class 2620 OID 17429)
-- Name: product_rating validachavepessoaavaliacaoproduto; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaavaliacaoproduto BEFORE UPDATE ON public.product_rating FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3347 (class 2620 OID 17430)
-- Name: product_rating validachavepessoaavaliacaoproduto2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaavaliacaoproduto2 BEFORE INSERT ON public.product_rating FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3336 (class 2620 OID 17431)
-- Name: accounts_payable validachavepessoacontapagar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar BEFORE UPDATE ON public.accounts_payable FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3337 (class 2620 OID 17432)
-- Name: accounts_payable validachavepessoacontapagar2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar2 BEFORE INSERT ON public.accounts_payable FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3338 (class 2620 OID 17434)
-- Name: accounts_payable validachavepessoacontapagarperson_vendor; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagarperson_vendor BEFORE UPDATE ON public.accounts_payable FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa2();


--
-- TOC entry 3339 (class 2620 OID 17435)
-- Name: accounts_payable validachavepessoacontapagarperson_vendor2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagarperson_vendor2 BEFORE INSERT ON public.accounts_payable FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa2();


--
-- TOC entry 3326 (class 2606 OID 17342)
-- Name: online_transaction billing_address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.online_transaction
    ADD CONSTRAINT billing_address_fk FOREIGN KEY (billing_address_id) REFERENCES public.address(id);


--
-- TOC entry 3327 (class 2606 OID 17347)
-- Name: online_transaction discount_coupon_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.online_transaction
    ADD CONSTRAINT discount_coupon_fk FOREIGN KEY (discount_coupon_id) REFERENCES public.discount_coupon(id);


--
-- TOC entry 3325 (class 2606 OID 17367)
-- Name: sale_invoice online_transaction_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_invoice
    ADD CONSTRAINT online_transaction_fk FOREIGN KEY (online_transaction_id) REFERENCES public.online_transaction(id);


--
-- TOC entry 3323 (class 2606 OID 17372)
-- Name: store_sale_item online_transaction_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store_sale_item
    ADD CONSTRAINT online_transaction_fk FOREIGN KEY (online_transaction_id) REFERENCES public.online_transaction(id);


--
-- TOC entry 3322 (class 2606 OID 17377)
-- Name: tracking_status online_transaction_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracking_status
    ADD CONSTRAINT online_transaction_fk FOREIGN KEY (online_transaction_id) REFERENCES public.online_transaction(id);


--
-- TOC entry 3328 (class 2606 OID 17352)
-- Name: online_transaction payment_method_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.online_transaction
    ADD CONSTRAINT payment_method_fk FOREIGN KEY (payment_method_id) REFERENCES public.payment_method(id);


--
-- TOC entry 3320 (class 2606 OID 17310)
-- Name: invoice_product_item product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_product_item
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3319 (class 2606 OID 17315)
-- Name: product_image product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3324 (class 2606 OID 17325)
-- Name: store_sale_item product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store_sale_item
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3331 (class 2606 OID 17420)
-- Name: product_rating product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_rating
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3321 (class 2606 OID 17266)
-- Name: invoice_product_item purchase_invoice_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_product_item
    ADD CONSTRAINT purchase_invoice_fk FOREIGN KEY (purchase_invoice) REFERENCES public.purchase_invoice(id);


--
-- TOC entry 3317 (class 2606 OID 25215)
-- Name: user_roles role_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 3329 (class 2606 OID 17357)
-- Name: online_transaction sale_invoice_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.online_transaction
    ADD CONSTRAINT sale_invoice_fk FOREIGN KEY (sale_invoice_id) REFERENCES public.sale_invoice(id);


--
-- TOC entry 3330 (class 2606 OID 17362)
-- Name: online_transaction shipping_address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.online_transaction
    ADD CONSTRAINT shipping_address_fk FOREIGN KEY (shipping_address_id) REFERENCES public.address(id);


--
-- TOC entry 3318 (class 2606 OID 17382)
-- Name: user_roles users_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT users_fk FOREIGN KEY (users_id) REFERENCES public.users(id);


-- Completed on 2023-06-25 19:39:05

--
-- PostgreSQL database dump complete
--

