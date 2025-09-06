import Button from "./Test";
import {useState} from "react";

export default function ButtonPress() {
    const [count, setCount] = useState(0);
    return (
        <>
            <Button count={count} setCount={setCount}/>
            <p> press {count}</p>
        </>
    );
}