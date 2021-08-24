import type { NextPage } from "next";
import Head from "next/head";
import styles from "../styles/Home.module.css";
import Secured from "./secured";

const IndexPage: NextPage = () => {
    return (
        <div className={styles.container}>
            <Head>
                <title>Admin Portal</title>
            </Head>

            <Secured />
        </div>
    );
};

export default IndexPage;
